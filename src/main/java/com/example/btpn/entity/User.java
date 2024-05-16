package com.example.btpn.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "m_merchant")
public class User {
    @javax.persistence.Id
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;
    private String name;
    private String address;
    @Column(length = 16)
    private String pan;
    @Column(name = "phone_number", length = 16)
    private String phoneNumber;
    private Long balance;

    public void setId(Long id) {
        this.id = String.valueOf(id);
    }

    public String getId() {
        return id;
    }
}
