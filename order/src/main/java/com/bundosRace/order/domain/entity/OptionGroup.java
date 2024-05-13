package com.bundosRace.order.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Table(name = "OPTION_GROUPS")
public class OptionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTION_GROUP_ID")
    private Long id;

    @Column(name = "OPTION_GROUP_NAME")
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "optionGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options = new ArrayList<>();

    @Setter
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public void addOption(Option option) {
        options.add(option);
        option.setOptionGroup(this);
    }
}
