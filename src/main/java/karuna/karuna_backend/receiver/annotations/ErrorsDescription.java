package karuna.karuna_backend.receiver.annotations;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import karuna.karuna_backend.exception.dto.JwtErrorResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Add recipient to database.", description = "Create new recipient in database.")
@ApiResponses({
        @ApiResponse(
                responseCode = "403",
                description = "Forbidden: You don't have permission to access this resource"),
        @ApiResponse(
                responseCode = "401",
                description = "Unauthorized",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = JwtErrorResponse.class))),
})
public @interface ErrorsDescription {
}
