package service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import domain.Word;
import service.ChineseSegment;

public class SegmentHGDImpl implements ChineseSegment {

	@Override
	public List<Word> segmentText(StringBuffer text) throws IOException {
		JsonArray json = null;
		List<Word> list = null;

		json = sendPost(text);
		list = analyzeJson(json);

		return list;
	}

	private JsonArray sendPost(StringBuffer text) throws IOException {
		if (null == text)
			return null;

		URL url = new URL("http://api.ltp-cloud.com/analysis/");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		String api_key = "k1b0Q3q7S6HCRnkynRinNmBeOIQXcMtG1EUqJjfA";
		String pattern = "pos";
		String format = "json";
		String utext = URLEncoder.encode(text.toString(), "utf-8");
		String has_key = "true";

		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		conn.connect();
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());

		String content = ("api_key=" + api_key + "&" + "text=" + utext + "&" + "format=" + format + "&" + "has_key="
				+ has_key + "&" + "pattern=" + pattern);

		out.writeBytes(content);
		out.flush();
		out.close();

		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

		JsonParser parser = new JsonParser(); // 创建JSON解析器
		JsonArray object = (JsonArray) parser.parse(reader); // 创建JsonObject对象
		conn.disconnect();

		return object;
	}

	private List<Word> analyzeJson(JsonArray json) {
		if (null == json)
			return null;

		List<Word> list = new LinkedList<>();
		HashMap<String, Word> map = new HashMap<>();
		JsonArray para = json;
		for (JsonElement ptemp : para) {
			JsonArray sent = ptemp.getAsJsonArray();
			for (JsonElement stemp : sent) {
				JsonArray words = stemp.getAsJsonArray();
				for (JsonElement wtemp : words) {
					JsonObject word = wtemp.getAsJsonObject();
					String pos = word.get("pos").getAsString();
					String cont = word.get("cont").getAsString();
					if (null == pos || null == cont || pos.isEmpty() || cont.isEmpty())
						continue;
					if (pos.equals("wp".intern()) || pos.equals("ws".intern()))
						continue;

					Word temp = map.get(cont);
					if (null == temp) {
						temp = new Word(cont, Integer.valueOf(1));
						temp.setType(pos);

						list.add(temp);
						map.put(cont, temp);
					} else {
						temp.addFrequancy();
					}
				}
			}
		}

		Collections.sort(list);
		Collections.reverse(list);

		return list;
	}

}
