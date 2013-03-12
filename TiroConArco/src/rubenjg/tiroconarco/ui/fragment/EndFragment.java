/**
 * Tiro con Arco, aplicación para dispositivos móviles
 * Copyright (C) 2013  Rubén Jiménez Goñi
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package rubenjg.tiroconarco.ui.fragment;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Comparator;

import rubenjg.tiroconarco.R;
import rubenjg.tiroconarco.ui.activity.ScoreActivity;
import rubenjg.tiroconarco.util.Score;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Fragmento que recibe los puntajes de una tirada.
 * 
 * @author Rubén Jiménez Goñi
 * 
 */
public class EndFragment extends DialogFragment {

	private static final int IDS[] = { R.id.fragment_end_score_1,
			R.id.fragment_end_score_2, R.id.fragment_end_score_3,
			R.id.fragment_end_score_4, R.id.fragment_end_score_5,
			R.id.fragment_end_score_6 };

	private TextView scores[];
	private WeakReference<ScoreActivity> activity;
	private int rowNumber;

	/**
	 * Crea una nueva instancia del fragmento.
	 * 
	 * @param a
	 *            Recibe una instancia de la actividad que crea el fragmento
	 *            para ejecutar callbacks.
	 * @param rowNumber
	 *            Número de fila de esta tirada en la matriz de la actividad
	 * @return La instancia del fragmento creada.
	 */
	public static EndFragment newInstance(ScoreActivity a, int rowNumber) {
		EndFragment f = new EndFragment();
		f.activity = new WeakReference<ScoreActivity>(a);
		f.rowNumber = rowNumber;
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_end, container, false);

		// Se busca el espacio para colocar los scores en el fragmento
		scores = new TextView[IDS.length];
		for (int i = 0; i < scores.length; ++i) {
			scores[i] = (TextView) v.findViewById(IDS[i]);
		}

		// Se crea un OnClickListener para todos los botones del fragmento
		MyOnClickListener l = new MyOnClickListener(this, scores);
		for (String s : Score.POSSIBLE_SCORES) {
			Button b = (Button) v.findViewWithTag(s);
			b.setOnClickListener(l);
		}

		// Título del fragmento
		getDialog().setTitle("Tirada " + (rowNumber + 1));
		
		return v;
	}

	/**
	 * Para el OnClickListener. Ejecuta el callback a la actividad de forma que
	 * el OnClickListener no necesita una referencia.
	 * 
	 * @param j
	 *            Indice de la columna
	 * @param score
	 *            Puntaje
	 */
	public void setScore(int j, String score) {
		activity.get().setScore(rowNumber, j, score);
	}

	/**
	 * OnClickListener para los botones del fragmento.
	 * 
	 * @author Rubén Jiménez Goñi
	 * 
	 */
	private static class MyOnClickListener implements View.OnClickListener {

		private EndFragment fragment;
		private TextView end[];
		private int i = 0;

		/**
		 * Constructor
		 * 
		 * @param endFragment
		 * 
		 * @param tagColor
		 *            Mapa que indica el color que debe tener cada puntaje
		 * @param end
		 *            Lista de TextViews del fragmento en los cuales se escribe
		 *            el puntaje del arquero
		 */
		public MyOnClickListener(EndFragment endFragment, TextView end[]) {
			this.fragment = endFragment;
			this.end = end;
		}

		/**
		 * Cada vez que se hace click en un botón se pone el puntaje indicado en
		 * el TextView correspondiente
		 */
		@Override
		public void onClick(View v) {
			if (i < 6) {
				end[i++].setText((String) v.getTag());
				sort();
				setColors();
			}
		}

		/**
		 * Ordena los puntajes de la lista de TextView
		 */
		private void sort() {

			// Se crea una copia de los puntajes
			String array[] = new String[end.length];
			for (int i = 0; i < end.length; ++i) {
				array[i] = end[i].getText().toString();
			}

			// Se ordena la copia
			Arrays.sort(array, new Comparator<String>() {

				@Override
				public int compare(String lhs, String rhs) {
					int lhsIndex = findIndex(lhs);
					int rhsIndex = findIndex(rhs);
					return lhsIndex == rhsIndex ? 0 : rhsIndex < lhsIndex ? 1
							: -1;
				}

				/**
				 * Dado un String busca el índice correspondiente en la lista de
				 * posibles puntajes
				 * 
				 * @param s
				 *            String a comparar
				 * @return
				 */
				public int findIndex(String s) {
					// Se inicializa en un valor alto para que los blancos
					// queden al final
					int index = Score.POSSIBLE_SCORES.length;
					for (int i = 0; i < Score.POSSIBLE_SCORES.length
							&& index == Score.POSSIBLE_SCORES.length; ++i) {
						if (s.equals(Score.POSSIBLE_SCORES[i])) {
							index = i;
						}
					}
					return index;
				}
			});

			// Se reasignan los valores a los TextViews
			for (int i = 0; i < end.length; ++i) {
				end[i].setText(array[i]);
				fragment.setScore(i, array[i]);
			}
		}

		/**
		 * Asigna el color indicado a todos los TextView según el puntaje que
		 * tienen como texto. Debe ejecutarse luego de ordenar los puntajes
		 * 
		 * @param t
		 *            TextView que requiere que se le asigne un color
		 */
		private void setColors() {
			Score score = Score.newInstance(fragment.getActivity()
					.getApplicationContext());
			for (TextView t : end) {
				Integer color = score.getColor(t.getText().toString());
				if (color != 0) {
					t.setTextColor(color);
				}
			}
		}

	}

}
