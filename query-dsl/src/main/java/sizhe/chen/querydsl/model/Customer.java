package sizhe.chen.querydsl.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cts_customer")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    @Id
    @Column(name = "cust_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custId;

    @Column(name = "cust_name")
    private String  custName;

    @Column(name="cust_address")
    private String custAddress;
}
