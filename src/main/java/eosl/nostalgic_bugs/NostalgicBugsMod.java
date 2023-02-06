package eosl.nostalgic_bugs;

import net.fabricmc.api.ModInitializer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


public class NostalgicBugsMod implements ModInitializer
{
	public static final Logger g_logger = LoggerFactory.getLogger("nostalgic_bugs");
	private static final String g_settingsFileName = "./nostalgic_bugs.cfg";
	public static @NotNull Settings g_settings = new Settings();


	@Override
	public void onInitialize()
	{
		reset();
	}


	public static void reset()
	{
		File file = new File(g_settingsFileName);

		try {
			if (file.createNewFile()) {
				g_logger.info("Create default settings file [" + g_settingsFileName + "]");
				g_settings = new Settings();
				g_settings.write(new FileWriter(file));
			}
			else {
				g_logger.info("Using setting file [" + g_settingsFileName + "]");
				g_settings = new Settings(new FileReader(file));
			}
		} catch (IOException e) {
			g_settings = new Settings();
			g_logger.error("Can not read settings file [" + g_settingsFileName + "]. The default one is using now");
		}
	}

}
