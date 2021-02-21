package pl.mvwojcik.model;

public enum Unit {
   ML1("ml",1),ML100("ml", 100),MG1("mg",0),G1("g",1),  G100("g", 100);
   private String name;
   private Integer value;

    Unit(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }
}
