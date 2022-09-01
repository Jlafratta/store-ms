package edu.store.client.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class PersonDTO {

    private Long id;

    @Length(max = 50)
    private String name;

    @Length(max = 50)
    private String surname;

    @NotNull
    private LocalDate birthDate;
}
