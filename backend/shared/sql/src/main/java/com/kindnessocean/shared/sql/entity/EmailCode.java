package com.kindnessocean.shared.sql.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emailCode")
public class EmailCode extends BaseEntity {

    @OneToOne(mappedBy = "code", fetch = FetchType.LAZY)
    private Email email;

    @Column(name = "expiredAt")
    private Date expiredAt;

    @Column(name = "code")
    private Integer code;
}
