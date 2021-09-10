package br.com.mesttra.roster.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.mesttra.roster.enums.Position;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Player implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @ApiModelProperty(
            value = "Player name",
            name = "name",
            dataType = "String",
            example = "João Almeida")
    private String name;

	@Enumerated(EnumType.STRING)
	private Position position;

	private Double salary;

	private boolean available;

}
