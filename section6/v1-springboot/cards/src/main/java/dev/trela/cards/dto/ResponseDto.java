package dev.trela.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Response Dto",
        description = "Schema to hold response information"
)
public record ResponseDto(
        @Schema(
                description = "Status code in of the response"
        )
        String statusCode,
                          @Schema (
                                  description = "Status message in the response"
                          )
                          String statusMsg) {

}
