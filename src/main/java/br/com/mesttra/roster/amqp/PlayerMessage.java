package br.com.mesttra.roster.amqp;

import br.com.mesttra.roster.entity.Player;
import br.com.mesttra.roster.enums.Position;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
public class PlayerMessage implements Serializable {

    private String name;

    @Enumerated(EnumType.STRING)
    private Position position;

    private Double salary;

    private boolean available;

    private String correlationId;

    public Player toEntity() {
        Player player = new Player();
        BeanUtils.copyProperties(this, player);
        return player;
    }
}
