package com.example.socksapp.controllers;

import com.example.socksapp.model.Socks;
import com.example.socksapp.services.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/socks")
@Tag(name = "Носки", description = "Учет товаров на складе")
public class SocksController {

    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @GetMapping("/{id}")
    @Operation(description = "Получение товара по его id")
    public Socks getSocks(@PathVariable Long id) {
        return socksService.getSocks(id);
    }

    @PostMapping
    @Operation(description = "Добавление новго товара")
    public void addSocks(@RequestBody Socks socks) {
        socksService.addSocks(socks);
    }

    @PutMapping("/{id}")
    @Operation(
            description = "Изменение товара по его id"
    )
    public void changeSocks(@PathVariable Long id, @RequestBody Socks socks) {
        socksService.changeSocks(id, socks);
    }

    @DeleteMapping("/{id}")
    @Operation(
            description = "Удаление товара по его id"
    )
    public void deleteSocks(@PathVariable Long id) {
        socksService.deleteSocks(id);
    }

    @GetMapping
    @Operation(
            description = "Получение списка всех товаров"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "товары получены",
                    content = {
                            @Content(
                                    mediaType = "socks/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Socks.class))

                            )
                    }
            )
    }
    )
    public String printSocks(@RequestBody Map<Long, Socks> noski) {
        return socksService.printAllSocks(noski);
    }
}
