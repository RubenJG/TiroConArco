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

import rubenjg.tiroconarco.R;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * @author Rubén Jiménez Goñi
 * 
 */
public class AboutFragment extends DialogFragment {

	public static AboutFragment newInstance() {
		return new AboutFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getDialog().setTitle("Acerca De");
		setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_Sherlock_Dialog);
		View v = inflater.inflate(R.layout.fragment_about, container, false);
		Button b = (Button) v.findViewById(R.id.fragment_about_button);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		return v;
	}

}
