package com.qcsi.reversetool;
import org.apache.commons.lang3.text.WordUtils;

import java.util.HashMap;

public class Converter {
    private static HashMap<String, String> typesMappings = new HashMap<>();

    static {
        typesMappings.put("bool", "boolean");
        typesMappings.put("\"char\"", "byte");
        typesMappings.put("integer", "int");
        typesMappings.put("int2", "short");
        typesMappings.put("int4", "int");
        typesMappings.put("int8", "long");
        typesMappings.put("float4", "float");
        typesMappings.put("float8", "double");
        typesMappings.put("char", "String");
        typesMappings.put("varchar", "String");
        typesMappings.put("text", "String");
        typesMappings.put("name", "String");
        typesMappings.put("bytea", "byte[]");
        typesMappings.put("date", "Date");
        typesMappings.put("time", "Time");
        typesMappings.put("timetz", "Time");
        typesMappings.put("timestamp", "Timestamp");
        typesMappings.put("timestamptz", "Timestamp");

        typesMappings.put("\"any\"", "Object");
        typesMappings.put("anyelement", "Object");
        typesMappings.put("anyarray", "Object[]");
        typesMappings.put("cstring", "String");
        typesMappings.put("record", "java.sql.ResultSet");
    }

    public static String toJavaType(String dbType){
        String javaType = null;
        if (dbType.contains("[]")) {
            javaType = typesMappings.get(dbType.substring(0, dbType.length() - 2));
            if (javaType != null)
                javaType += "[]";
        }
        else
            javaType = typesMappings.get(dbType);

        return javaType;
    }

    public static String toCamelCase(String name){
        return WordUtils.uncapitalize(toPascalCase(name));
    }

    public static String toPascalCase(String name){
        return WordUtils.capitalize(name, '_').replaceAll("_", "");
    }
}
