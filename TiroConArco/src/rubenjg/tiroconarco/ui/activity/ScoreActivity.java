/**
 * Tiro con Arco, aplicaci�n para dispositivos m�viles
 * Copyright (C) 2013  Rub�n Jim�nez Go�i
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
package rubenjg.tiroconarco.ui.activity;

import rubenjg.tiroconarco.R;
import rubenjg.tiroconarco.ui.fragment.AboutFragment;
import rubenjg.tiroconarco.ui.fragment.EndFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

/**
 * @author Rub�n Jim�nez Go�i
 * 
 */
public class ScoreActivity extends SherlockFragmentActivity {

	private static final String ABOUT_TAG = "ABOUT_TAG";
	private static final String END_TAG = "END_TAG";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);

		// Se busca la tabla para para los puntajes
		TableLayout table = (TableLayout) findViewById(R.id.activity_score_table);

		// Se le insertan las filas y columnas respectivas
		for (int i = 0; i < 6; ++i) {
			getLayoutInflater().inflate(R.layout.view_score_table_row, table);
			TableRow row = (TableRow) table.getChildAt(i);
			for (int j = 0; j < 6; ++j) {
				getLayoutInflater().inflate(R.layout.view_cell, row);
				TextView cell = (TextView) row.getChildAt(j);
				cell.setTextColor(getResources().getColor(R.color.Yellow));
				cell.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						EndFragment.newInstance().show(getSupportFragmentManager(), END_TAG);
					}
				});
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.activity_score, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean eventResolved = true;
		switch (item.getItemId()) {
		case R.id.about:
			AboutFragment.newInstance().show(getSupportFragmentManager(),
					ABOUT_TAG);
		default:
			eventResolved = false;
		}
		return eventResolved;
	}
}
