package br.com.mesttra.roster.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MailResponseDTO {

    private Long id;
    private LocalDateTime timeSent;
    private String destination;
    private String content;

}
