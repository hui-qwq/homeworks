package code;

public class work18 {
    public enum UserRole {
        ADMIN {
            @Override
            public String getPerm() {
                return "增删改查";
            }
        },
        USER {
            @Override
            public String getPerm() {
                return "查询、修改个人信息";
            }
        },
        GUEST {
            @Override
            public String getPerm() {
                return "只读访问";
            }
        };

        public abstract String getPerm();
    }

    public static void main(String[] args) {
        for (UserRole r : UserRole.values()) {
            System.out.println(r + "权限：" + r.getPerm());
        }
    }
}