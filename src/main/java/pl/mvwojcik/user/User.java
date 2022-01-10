package pl.mvwojcik.user;

//
//@Entity
//@Table(name = "USERS")
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//public class User {
//
//    @Id
//    @GeneratedValue(generator = "user_generation", strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "user_generation", sequenceName = "user_seq", allocationSize = 5, initialValue = 20)
//    @Column(name = "id", updatable = false, unique = true)
//    private Integer id;
//
//    private String username;
//
//    private Double weight;
//
//    private Double height;
//
//    private Integer dailyKcal;
//
//    private Integer activeLevel;
//
//    @ManyToMany(mappedBy = "users")
//    private Set<DietPlan> dietPlans;
//
//    public void addPlan(DietPlan dietPlan) {
//        this.dietPlans.add(dietPlan);
//    }
//}
