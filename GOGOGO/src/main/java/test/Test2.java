package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yjcao
 * @date 2020/6/17 17:16
 */
public class Test2 {

    public static void main(String[] args) {
//        JSONArray arrays = initJsonArrayData();
//        System.out.println(arrays);
//        arrays = filteraaa(arrays);
//        System.out.println(arrays);
        List list = initListData();
        System.out.println(list);
        list = filterList(list);
        System.out.println(list);
        System.out.println("你好");


    }

    private static JSONArray filterJson(JSONArray arrays) {
        List<Object> arraysFilted = arrays.parallelStream().map(a -> {
            JSONObject jo = (JSONObject) a;
            jo.put("key", jo.getInteger("key") + 1);
            return jo;
        }).collect(Collectors.toList());

        return JSONArray.parseArray(JSON.toJSONString(arraysFilted));
    }

    private static List<Integer> filterList(List<Integer> list) {
        return list.parallelStream().map(a -> {
            return a + 1;
        }).collect(Collectors.toList());
    }

    private static JSONArray initJsonArrayData() {
        JSONArray ja = new JSONArray();
        for (int i = 1; i <= 10; i++) {
            JSONObject o = new JSONObject();
            o.put("key", i);
            ja.add(o);
        }
        return ja;
    }

    private static List initListData() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            list.add(i);
        }
        return list;
    }

    private static JSONArray bbbbb(JSONArray ja) {
        int pageSize = 3;
        int pageIndex = 1;

        JSONArray filterDatas = new JSONArray();
        int startIndex = pageSize * (pageIndex - 1);
        int endIndex = pageIndex * pageSize - 1;

        int count = ja.size();
        if (startIndex > count - 1) {
//            System.out.println("startIndex:" + startIndex);
//            System.out.println("endIndex:" + endIndex);
            ja.clear();

        } else {
            if (endIndex > count - 1) {
                endIndex = count - 1;
            }

//            System.out.println("startIndex:" + startIndex);
//            System.out.println("endIndex:" + endIndex);
            for (int i = startIndex; i <= endIndex; i++) {
                filterDatas.add(ja.getJSONObject(i));
            }
            ja.clear();
            ja = filterDatas;
        }
//        System.out.println("filterDatas:" + filterDatas);
//        System.out.println("ja:" + ja);
        return ja;
    }

    private static void aaaa(JSONArray ja) {
        Iterator<Object> o = ja.iterator();
        while (o.hasNext()) {
            JSONObject jo = (JSONObject) o.next();
            if (jo.getIntValue("key") == 2) {
                o.remove();
            }
        }
    }

}
