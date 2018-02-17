package gruntpie224.crockpot.util;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class CPSounds {
	public static SoundEvent snd_pot_open;
	public static SoundEvent snd_pot_close;
	public static SoundEvent snd_cooking_done;
	public static SoundEvent snd_cooking;
	
	public static void initSounds()
	{
		snd_pot_open = new SoundEvent(new ResourceLocation("cp", "pot_open"));
		snd_pot_close = new SoundEvent(new ResourceLocation("cp", "pot_close"));
		snd_cooking_done = new SoundEvent(new ResourceLocation("cp", "cooking_done"));
		snd_cooking = new SoundEvent(new ResourceLocation("cp", "cooking"));
	}
}
