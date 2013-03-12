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
package rubenjg.tiroconarco.util;

import java.util.HashMap;

import rubenjg.tiroconarco.R;
import android.content.Context;

/**
 * Singleton para manejar operaciones relacionadas con los puntajes
 * 
 * @author Rubén Jiménez Goñi
 * 
 */
public class Score {

	public static Score score;
	public static final String POSSIBLE_SCORES[] = { "X", "10", "9", "8", "7",
			"6", "5", "4", "3", "2", "1", "M" };
	public static final int COLORS[] = { R.color.Yellow, R.color.Yellow,
			R.color.Yellow, R.color.Red, R.color.Red, R.color.Blue,
			R.color.Blue, R.color.Black, R.color.Black, R.color.White,
			R.color.White, R.color.Green };

	private HashMap<String, Integer> map = new HashMap<String, Integer>();

	/**
	 * Constructor
	 * 
	 * @param context
	 *            Debe ser el contexto de la aplicación preferiblemente
	 * @return
	 */
	public static Score newInstance(Context context) {
		if (score == null) {
			score = new Score();
			// Se crea un mapa que indica el color que debe tener cada puntaje
			for (int i = 0; i < POSSIBLE_SCORES.length; ++i) {
				score.map.put(POSSIBLE_SCORES[i], context.getResources()
						.getColor(COLORS[i]));
			}
		}
		return score;
	}

	private Score() {
	}

	public int getColor(String score) {
		int color = 0;
		if (map.get(score) != null) {
			color = map.get(score);
		}
		return color;
	}

}
