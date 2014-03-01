package net.trizmo.mtgcards;

import java.awt.Font;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FontLoader {

	private static String[] fontNames = { "Wizards Magic.ttf" };
	
	private static Map<String, Font> cache = new ConcurrentHashMap<String, Font>(fontNames.length);
	static {
		for (String name : fontNames) {
			cache.put(name, getFont(name));
		}
	}
	
	public static Font getFont(String name)
	{
		Font font = null;
		if(cache != null)
		{
			if((font = cache.get(name)) != null)
			{
				return font;
			}
		}
		
		String fName = "res/General/" + name;
		
		try
		{
			InputStream is = FontLoader.class.getResourceAsStream(fName);
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		}catch (Exception ex)
		{
			ex.printStackTrace();
			font = new Font("serif", Font.PLAIN, 24);
		}
		return font;
	}
}
