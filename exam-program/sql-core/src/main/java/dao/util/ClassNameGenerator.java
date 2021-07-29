package dao.util;

@Deprecated
public class ClassNameGenerator {
    public static String getGeneratedClassName(Class entityClass) {
        String className = entityClass.getName();
        StringBuilder nameBuilder = new StringBuilder();
        String[] splitName = className.split("\\.");

        for (int i = 0; i < splitName.length - 1; i++) {
            nameBuilder.append(splitName[i]).append(".");
        }
        char[] chars = splitName[splitName.length - 1].toCharArray();
        nameBuilder.append(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] >= 65 && chars[i] <= 90) {
                nameBuilder.append('_');
                nameBuilder.append((char) (chars[i] + 32));
            } else
                nameBuilder.append(chars[i]);
        }
        System.out.println(nameBuilder);
        return nameBuilder.toString();
    }
}
