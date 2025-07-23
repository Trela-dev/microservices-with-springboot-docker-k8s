package dev.trela.message.dto;

public record AccountsMsgDto(
        Long accountNumber,
        String name,
        String email,
        String mobileNumber
) {
}
