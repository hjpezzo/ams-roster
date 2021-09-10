package br.com.mesttra.roster.amqp;

import br.com.mesttra.roster.entity.Player;
import br.com.mesttra.roster.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ContractCosumer {

    PlayerService playerService;
    RabbitTemplate rabbitTemplate;

    public ContractCosumer(PlayerService playerService, RabbitTemplate rabbitTemplate) {
        this.playerService = playerService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "contract-to-roster-queue")
    public void consumer(@Payload PlayerMessage player) {
        Logger logger = LoggerFactory.getLogger(ContractCosumer.class);
        logger.info(player.toString());
        playerService.addPlayer(player.toEntity());
        rabbitTemplate.convertAndSend(AMQPConfig.EXCHANGE_NAME, AMQPConfig.ROUTING_KEY_ROSTER_TO_CONTRACT, player);
    }

}
