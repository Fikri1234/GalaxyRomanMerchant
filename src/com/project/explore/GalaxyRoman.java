/**
 * 
 */
package com.project.explore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author user on 2022-04-14 13:42:21.733
 *
 */
public class GalaxyRoman {
	
	private final static String HOW_MUCH = "how much is ";
	private final static String HOW_MANY_CREDIT = "how many Credits is ";
	private final static String Silver = "Silver";
	private final static String Gold = "Gold";
	private final static String Iron = "Iron";

	// input Roman
	private final static Map<String, String> romanMap = new HashMap<String, String>();
	private final static Map<String, Double> treasureMap = new HashMap<String, Double>();
	
	// regex for validate repeated symbol
	private final static String regex = "(?<=(.))(?!\\1)";
	
	// arrayList Roman
	private final static List<String> al = Arrays.asList("I", "X", "C", "M");
	
	// arrayList Roman for substract
	private final static List<String> al_I = Arrays.asList("V", "X");
	private final static List<String> al_X = Arrays.asList("L", "C");
	private final static List<String> al_C = Arrays.asList("D", "M");
	
	private final static String errorResponseStr = "I have no idea what you are talking about. ";
	
	public static void main(String[] args) {
		
		try {
			Scanner sc = new Scanner(System.in);
			
			String msg = "";
			
			while (sc.hasNextLine()) {

				// String input
				String str = sc.nextLine();
				
				// replace question sign
				str = str.replaceAll("\\?", "");
				
				// response
				Map<String, Object> res = new HashMap<String, Object>();
				res.put("isError", true);
				res.put("msg", errorResponseStr);

				String[] spl = str.split(" ");
				if (spl.length == 3) {
					romanMap.put(spl[0], spl[2]);
					
					res.put("isError", false);
					res.put("msg", "");
				}

				// Mapping Roman
				Map<String, Integer> map = getMapRoman();

//				String s = "MMMDCCXXIV";

				String s = "";
				
				String responseRomanStr = "";
				if (str.startsWith(HOW_MUCH)) {
					String[] getHowMuchIsSplit = str.split(HOW_MUCH);
					String[] convertToRomanSplit = getHowMuchIsSplit[1].split(" ");
					responseRomanStr = getHowMuchIsSplit[1];

					s = getConvertToRoman(convertToRomanSplit);
					res = convertRomanToNumeral(s, map);
				}
				
				String treasure = "";
				double total = 0.0;
				if (str.contains(Silver)) {
					treasure = Silver;
					if (str.startsWith(HOW_MANY_CREDIT)) {
						String silverStr = str.replace(HOW_MANY_CREDIT, "");
						String[] silverSplit = silverStr.split(Silver);
						
						responseRomanStr = silverSplit[0];
						
						silverStr = silverStr.replace(Silver, "");
						String[] convertToRomanSplit = silverStr.split(" ");
						
						s = getConvertToRoman(convertToRomanSplit);
						
						res = convertRomanToNumeral(s, map);
						if (!(boolean) res.get("isError")) {
							total = treasureMap.get(Silver) * ((Double) res.get("num"));
						}
					} else {
						String[] creditSplit = str.split(Silver);
						String[] convertToRomanSplit = creditSplit[0].split(" ");
						responseRomanStr = creditSplit[0];
						s = getConvertToRoman(convertToRomanSplit);
						
						String numStr = findNumberFromString(creditSplit[1]);
						
						res = convertRomanToNumeral(s, map);
						if (!(boolean) res.get("isError")) {
							double calc = Double.valueOf(numStr) / ((Double) res.get("num"));
							treasureMap.put(Silver, calc);
						}
					}
				} else if (str.contains(Gold)) {
					treasure = Gold;
					if (str.startsWith(HOW_MANY_CREDIT)) {
						String goldStr = str.replace(HOW_MANY_CREDIT, "");
						String[] goldSplit = goldStr.split(Gold);
						
						responseRomanStr = goldSplit[0];
						
						goldStr = goldStr.replace(Gold, "");
						String[] convertToRomanSplit = goldStr.split(" ");
						
						s = getConvertToRoman(convertToRomanSplit);
						
						res = convertRomanToNumeral(s, map);
						if (!(boolean) res.get("isError")) {
							total = treasureMap.get(Gold) * ((Double) res.get("num"));
						}
					} else {
						String[] creditSplit = str.split(Gold);
						String[] convertToRomanSplit = creditSplit[0].split(" ");
						responseRomanStr = creditSplit[0];
						s = getConvertToRoman(convertToRomanSplit);
						
						String numStr = findNumberFromString(creditSplit[1]);
						
						res = convertRomanToNumeral(s, map);
						if (!(boolean) res.get("isError")) {
							double calc = Double.valueOf(numStr) / ((Double) res.get("num"));
							treasureMap.put(Gold, calc);
						}
					}
				} else if (str.contains(Iron)) {
					treasure = Iron;
					if (str.startsWith(HOW_MANY_CREDIT)) {
						String ironStr = str.replace(HOW_MANY_CREDIT, "");
						String[] ironSplit = ironStr.split(Iron);
						
						responseRomanStr = ironSplit[0];
						
						ironStr = ironStr.replace(Iron, "");
						String[] convertToRomanSplit = ironStr.split(" ");
						
						s = getConvertToRoman(convertToRomanSplit);
						
						res = convertRomanToNumeral(s, map);
						if (!(boolean) res.get("isError")) {
							total = treasureMap.get(Iron) * ((Double) res.get("num"));
						}
					} else {
						String[] creditSplit = str.split(Iron);
						String[] convertToRomanSplit = creditSplit[0].split(" ");
						responseRomanStr = creditSplit[0];
						s = getConvertToRoman(convertToRomanSplit);
						
						String numStr = findNumberFromString(creditSplit[1]);
						
						res = convertRomanToNumeral(s, map);
						if (!(boolean) res.get("isError")) {
							double calc = Double.valueOf(numStr) / ((Double) res.get("num"));
							treasureMap.put(Iron, calc);
						}
					}
				}
				
				// response
				if (!(boolean) res.get("isError")) {
					if (str.startsWith(HOW_MUCH)) {
						System.out.println(responseRomanStr + "is " + ((int)((double) res.get("num"))));
					} else if (str.startsWith(HOW_MANY_CREDIT)) {
						if (str.contains(Silver)) {
							System.out.println(responseRomanStr + treasure +" is " + ((int)total) + " Credits");
						} else if (str.contains(Gold)) {
							System.out.println(responseRomanStr + treasure +" is " + ((int)total) + " Credits");
						} else if (str.contains(Iron)) {
							System.out.println(responseRomanStr + treasure +" is " + ((int)total) + " Credits");
						}
					}
				} else {
					msg = (String) res.get("msg");
					break;
				}
				
				if ((boolean) res.get("isError")) {
					msg = (String) res.get("msg");
					break;
				}
			}
			
			System.out.println(errorResponseStr + msg);
			System.exit(0);
			sc.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(errorResponseStr + "Error input : " + e.getMessage());
			System.exit(0);
		}
	}
	
	private static Map<String, Integer> getMapRoman() {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("I", 1);
		map.put("V", 5);
		map.put("X", 10);
		map.put("L", 50);
		map.put("C", 100);
		map.put("D", 500);
		map.put("M", 1000);
		return map;
		
	}
	
	private static Map<String, Object> convertRomanToNumeral(String s, Map<String, Integer> map) {
		
		double num = 0.0;
		
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("isError", false);
		res.put("msg", "");
		
		if (s != null & s != "") {
			
			// validate duplicate Roman
			String[] a = s.split(regex);
			for (int i = 0; i < a.length; i++) {
				char c1 = s.charAt(i);
				String str = String.valueOf(c1);
				for (String sd: al) {
					if (str.equals(sd)) {
						if (a[i].length() > 3) {
							res.put("isError", true);
							res.put("msg", errorResponseStr +"Invalid String " +a[i]+" more than 3");
							break;
						}
					}
				}
			}
			
			if (!(boolean) res.get("isError")) {
				for (int i = 0; i < s.length(); i++) {

					char c1 = s.charAt(i);
					String rom1 = String.valueOf(c1);

					Integer x = map.get(rom1);
					if (x == null) {
						res.put("isError", true);
						res.put("msg", errorResponseStr + rom1 + " is invalid Roman Numeral");
						break;
					}

					int j = i + 1;
					if (j == s.length()) {
						num += x;
						break;
					}

					// ============================

					char c2 = s.charAt(j);
					String rom2 = String.valueOf(c2);

					Integer y = map.get(rom2);
					if (y == null) {
						res.put("isError", true);
						res.put("msg", errorResponseStr + rom2 + " is invalid Roman Numeral");
						break;
					}

					if (x < y) {
						boolean isSubstractByLarge = false;
						// Validate substract condition
						switch (rom1) {
						case "I":
							for (String str : al_I) {
								if (rom2.equals(str)) {
									isSubstractByLarge = true;
									break;
								}
							}
							break;
						case "X":
							for (String str : al_X) {
								if (rom2.equals(str)) {
									isSubstractByLarge = true;
									break;
								}
							}
							break;
						case "C":
							for (String str : al_C) {
								if (rom2.equals(str)) {
									isSubstractByLarge = true;
									break;
								}
							}
							break;
						case "M":
							isSubstractByLarge = true;
							break;

						default:
							break;
						}
						if (isSubstractByLarge) {
							num += (y - x);
							i++;
						} else {
							num += x;
						}
					} else {
						num += x;
					}
				}
			}
		} else {
			res.put("isError", true);
			res.put("msg", errorResponseStr + "String empty/null");
		}
		
		res.put("num", num);
		
		return res;
	}
	
	private static String getConvertToRoman(String[] convertToRomanSplit) {
		String s = "";
		for (int i = 0; i < convertToRomanSplit.length; i++) {
			if (((convertToRomanSplit[i]) != null) && ((convertToRomanSplit[i]) != "")) {
				s += romanMap.get(convertToRomanSplit[i]);
			}
		}
		return s;
	}
	
	private static String findNumberFromString(String str) {
		char[] chars = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char c : chars) {
			if (Character.isDigit(c)) {
				sb.append(c);
			}
		}
		
		return sb.toString();
	}
}
