package lk.benjarong.ems.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@Table(name = "month")
public class MonthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "monthId")
    private Long id;

    private String name;

    // @JsonIgnore
    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "monthEntity", cascade = CascadeType.ALL)
    // private List<AttendanceEntity> attendances;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "monthEntity", cascade = CascadeType.ALL)
    private List<SalaryEntity> salaries;
    
}
