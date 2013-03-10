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

import java.util.HashMap;

import rubenjg.tiroconarco.R;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author Rubén Jiménez Goñi
 * 
 */
public class EndFragment extends DialogFragment {

	private static final String BUTTONS[] = { "X", "10", "9", "8", "7", "6",
			"5", "4", "3", "2", "1", "M" };
	private static final int COLORS[] = { R.color.Yellow, R.color.Yellow,
			R.color.Yellow, R.color.Red, R.color.Red, R.color.Blue,
			R.color.Blue, R.color.Black, R.color.Black, R.color.White,
			R.color.White, R.color.Green };

	private TextView scores[];
	private HashMap<String, Integer> tagColor = new HashMap<String, Integer>();

	public static EndFragment newInstance() {
		return new EndFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_end, container, false);

		LinearLayout end = (LinearLayout) v.findViewById(R.id.fragmend_end);
		scores = new TextView[end.getChildCount()];
		for (int i = 0; i < scores.length; ++i) {
			scores[i] = (TextView) end.getChildAt(i);
		}

		for (int i = 0; i < BUTTONS.length; ++i) {
			tagColor.put(BUTTONS[i], getResources().getColor(COLORS[i]));
		}

		MyOnClickListener l = new MyOnClickListener(tagColor, scores);
		for (String s : BUTTONS) {
			Button b = (Button) v.findViewWithTag(s);
			b.setOnClickListener(l);
		}

		return v;
	}

	private static class MyOnClickListener implements View.OnClickListener {

		private TextView end[];
		private HashMap<String, Integer> tagColor;
		private int i = 0;

		public MyOnClickListener(HashMap<String, Integer> tagColor,
				TextView end[]) {
			this.end = end;
			this.tagColor = tagColor;
		}

		@Override
		public void onClick(View v) {
			if (i < 6) {
				end[i++].setText((String) v.getTag());
				setColors();
				sort();
			}
		}

		private void setColors() {
			for (TextView t : end) {
				Integer color = tagColor.get(t.getText());
				if (color != null) {
					t.setTextColor(color);
				}
			}
		}

		private void sort() {

		}
	}

}
