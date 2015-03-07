package aweber.phandroid;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import android.app.Activity;
import android.content.Context;

public class PropertiesHandler {

	private static final String PROPERTY_FILE_NAME = "phandroid.props";
	
	/** load the properties file where app state is stored. */
	public static Properties loadProperties(final Activity activity) {
		final Properties props = new Properties();
		FileInputStream propsIn = null;
		try {
			propsIn = activity.openFileInput(PROPERTY_FILE_NAME);
			props.load(propsIn);
		} catch (FileNotFoundException e) {
			// property file doesn't exist yet, that's ok
		} catch (IOException e) {
			// error while reading input strean (TODO what to do?)
			throw new RuntimeException(e);
		} finally {
			if (propsIn != null) {
				try {
					propsIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return props;
	}
	
	/** save the properties file where app state is stored. */
	public static void saveProperties(final Activity activity, final Properties props) {
		FileOutputStream propsOut = null;
		try {
			// create property file not readable by other apps
			propsOut = activity.openFileOutput(PROPERTY_FILE_NAME, Context.MODE_PRIVATE);
			props.store(propsOut, null);
		} catch (IOException e) {
			e.printStackTrace(); // TODO can this happen? openFileOutput creates the file if it doesn't exist
		} finally {
			if (propsOut != null) {
				try {
					propsOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
