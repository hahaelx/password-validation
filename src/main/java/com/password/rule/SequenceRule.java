package com.password.rule;

import java.util.HashMap;

public class SequenceRule extends BaseRule {
    int minSrqencelength = 2;
    @Override
    public boolean validate(String value) {
        return this.isSequence(value);
    }

    @Override
    public String getFailedMessage() {
        return this.properties.getProperty("validation.rule.sequence.message");
    }

    private boolean isSequence(String str) {
        HashMap<String, Integer> stringIndexMap = null;
        int len = str.length();
        for (int i = minSrqencelength; i < len; i++) {
            stringIndexMap = new HashMap<String, Integer>();
            for (int j = 0; j + i <= len; j++) {
                String sub = str.substring(j, j + i);
                if (stringIndexMap.get(sub) == null) {
                    stringIndexMap.put(sub, j + i - 1);
                } else {
                    int prevlastIndex = stringIndexMap.get(sub);
                    if (prevlastIndex == j - 1) return false;
                }
            }
        }
        return true;
    }

}
