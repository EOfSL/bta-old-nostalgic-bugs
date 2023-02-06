package eosl.nostalgic_bugs;

import net.minecraft.src.NBTTagCompound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.io.*;
import java.util.Properties;


// Can't use GameSettings and Option classes, so I need to write own little code:
public class Settings
{
	public final boolean m_boatWaterLift;
	public final boolean m_ladderGaps;
	public final boolean m_minecartBoosters;
	public final boolean m_southEastRuleForLiquids;
	@Range(from = 1, to = Integer.MAX_VALUE)
	public final int m_fireTickRate;
	public final boolean m_infiniteFireSpread;


	/**
	 * Get default settings.
	 */
	public Settings()
	{
		m_boatWaterLift = true;
		m_ladderGaps = true;
		m_minecartBoosters = true;
		m_southEastRuleForLiquids = true;
		m_fireTickRate = 10;
		m_infiniteFireSpread = true;
	}


	/**
	 * Get settings from a Reader.
	 * @param a_settings Reader.
	 */
	public Settings(@NotNull Reader a_settings) throws IOException
	{
		Properties properties = new Properties();
		properties.load(a_settings);

		m_boatWaterLift = getProperty(properties, "boatWaterLift", true);
		m_ladderGaps = getProperty(properties, "ladderGaps", true);
		m_minecartBoosters = getProperty(properties, "minecartBoosters", true);
		m_southEastRuleForLiquids = getProperty(properties, "southEastRuleForLiquids", true);
		int fireTickRate = getProperty(properties, "fireTickRate", 10);
		m_infiniteFireSpread = getProperty(properties, "infiniteFireSpread", true);

		if (fireTickRate < 1) {
			NostalgicBugsMod.g_logger.error("Option [" + "fireTickRate" + "] must be greater than 0. It set now ot 10");
			m_fireTickRate = 10;
		}
		else
			m_fireTickRate = fireTickRate;
	}


	/**
	 * Get settings from an NBT.
	 * @param a_settings An NBT.
	 */
	@SuppressWarnings("unused") // for the future
	public Settings(@NotNull NBTTagCompound a_settings)
	{
		m_boatWaterLift = getProperty(a_settings, "boatWaterLift", true);
		m_ladderGaps = getProperty(a_settings, "ladderGaps", true);
		m_minecartBoosters = getProperty(a_settings, "minecartBoosters", true);
		m_southEastRuleForLiquids = getProperty(a_settings, "southEastRuleForLiquids", true);
		int fireTickRate = getProperty(a_settings, "fireTickRate", 10);
		m_infiniteFireSpread = getProperty(a_settings, "infiniteFireSpread", true);

		if (fireTickRate < 1) {
			NostalgicBugsMod.g_logger.error("Option [" + "fireTickRate" + "] must be greater than 0. It set now ot 10");
			m_fireTickRate = 10;
		}
		else
			m_fireTickRate = fireTickRate;
	}


	public void write(@NotNull Writer a_settings) throws IOException
	{
		Properties properties = new Properties();

		properties.setProperty("boatWaterLift", String.valueOf(m_boatWaterLift));
		properties.setProperty("ladderGaps", String.valueOf(m_ladderGaps));
		properties.setProperty("minecartBoosters", String.valueOf(m_minecartBoosters));
		properties.setProperty("southEastRuleForLiquids", String.valueOf(m_southEastRuleForLiquids));
		properties.setProperty("fireTickRate", String.valueOf(m_fireTickRate));
		properties.setProperty("infiniteFireSpread", String.valueOf(m_infiniteFireSpread));

		properties.store(a_settings, null);
	}


	/**
	 * Write the settings into an NBT.
	 * @param a_settings An NBT.
	 */
	@SuppressWarnings("unused") // for the future
	public void writeToNBT(@NotNull NBTTagCompound a_settings)
	{
		a_settings.setBoolean("boatWaterLift", m_boatWaterLift);
		a_settings.setBoolean("ladderGaps", m_ladderGaps);
		a_settings.setBoolean("minecartBoosters", m_minecartBoosters);
		a_settings.setBoolean("southEastRuleForLiquids", m_southEastRuleForLiquids);
		a_settings.setInteger("fireTickRate", m_fireTickRate);
		a_settings.setBoolean("infiniteFireSpread", m_infiniteFireSpread);
	}


	@SuppressWarnings("SameParameterValue")
	private static boolean getProperty(
			@NotNull Properties a_properties,
			@NotNull String a_key,
			boolean a_defaultValue)
	{
		String res = a_properties.getProperty(a_key);

		if (res == null) {
			NostalgicBugsMod.g_logger.error("Missing [" + a_key + "] param in client settings");
			return a_defaultValue;
		}

		return Boolean.parseBoolean(res);
	}


	@SuppressWarnings("SameParameterValue")
	private static int getProperty(
			@NotNull Properties a_properties,
			@NotNull String a_key,
			@Range(from = 0, to = Integer.MAX_VALUE) int a_defaultValue)
	{
		String res = a_properties.getProperty(a_key);

		if (res == null) {
			NostalgicBugsMod.g_logger.error("Missing [" + a_key + "] param in client settings");
			return a_defaultValue;
		}

		return Integer.parseInt(res);
	}


	@SuppressWarnings("SameParameterValue")
	private static boolean getProperty(
			@NotNull NBTTagCompound a_properties,
			@NotNull String a_key,
			boolean a_defaultValue)
	{
		if (!a_properties.hasKey(a_key)) {
			NostalgicBugsMod.g_logger.error("Missing [" + a_key + "] param in server settings");
			return a_defaultValue;
		}

		return a_properties.getBoolean(a_key);
	}


	@SuppressWarnings("SameParameterValue")
	private static int getProperty(
			@NotNull NBTTagCompound a_properties,
			@NotNull String a_key,
			@Range(from = 0, to = Integer.MAX_VALUE) int a_defaultValue)
	{
		if (!a_properties.hasKey(a_key)) {
			NostalgicBugsMod.g_logger.error("Missing [" + a_key + "] param in server settings");
			return a_defaultValue;
		}

		return a_properties.getInteger(a_key);
	}
}

