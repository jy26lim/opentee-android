package fi.aalto.ssg.opentee_mainapp;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by code on 6/24/15.
 */
public class Utils {

    // Executes UNIX command.
    public static String execUnixCommand(String command) throws IOException, InterruptedException {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            int read;
            char[] buffer = new char[4096];
            StringBuffer output = new StringBuffer();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();
            process.waitFor();
            return output.toString();
    }

    public static void copyFromAssetsToAppDir(Context context, String assetFile, String outputPath) throws IOException {
            InputStream in = context.getAssets().open(assetFile);

            FileOutputStream out = new FileOutputStream(outputPath);
            int read;
            byte[] buffer = new byte[4096];
            while ((read = in.read(buffer)) > 0) {
                out.write(buffer, 0, read);
            }
            out.close();
            in.close();
    }


}
