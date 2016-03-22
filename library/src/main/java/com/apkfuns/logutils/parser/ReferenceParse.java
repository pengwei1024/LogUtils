package com.apkfuns.logutils.parser;

import com.apkfuns.logutils.Parser;
import com.apkfuns.logutils.utils.CommonUtil;

import java.lang.ref.Reference;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Vector;

/**
 * Created by pengwei on 16/3/22.
 */
public class ReferenceParse implements Parser<Reference> {
    @Override
    public Class<Reference> parseClassType() {
        return Reference.class;
    }

    @Override
    public String parseString(Reference reference) {
        Object actual = reference.get();
        StringBuilder builder = new StringBuilder(reference.getClass().getSimpleName() + "<"
                + actual.getClass().getSimpleName() + "> {");
        builder.append("→" + CommonUtil.objectToString(actual));
        return builder.toString() + "}";
    }
}
