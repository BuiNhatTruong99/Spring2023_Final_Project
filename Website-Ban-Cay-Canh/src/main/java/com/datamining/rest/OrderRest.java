package com.datamining.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datamining.DTO.OrderDTO;
import com.datamining.DTO.ProductDTO;
import com.datamining.entity.Order;
import com.datamining.entity.OrderDetail;
import com.datamining.entity.Product;
import com.datamining.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.RequiredArgsConstructor;
import lombok.var;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderRest {
	private final OrderService orderService;
	
	@GetMapping()
	public ObjectResponse getAll() {
		var orders = orderService.findAll();
		var orderDTO = orders.stream().map(OrderDTO::convert).collect(Collectors.toList());
		return new ObjectResponse("ok", orderDTO, HttpStatus.OK.value());
	}

	@GetMapping("/completed")
	public ObjectResponse getAllByCompleted() {
		var orders = orderService.findAllByCompleted();
		var orderDTO = orders.stream().map(OrderDTO::convert).collect(Collectors.toList());
		return new ObjectResponse("ok", orderDTO, HttpStatus.OK.value());
	}
	
	@GetMapping("/canceled")
	public ObjectResponse getAllByCanceled() {
		var orders = orderService.findAllByCanceled();
		var orderDTO = orders.stream().map(OrderDTO::convert).collect(Collectors.toList());
		return new ObjectResponse("ok", orderDTO, HttpStatus.OK.value());
	}

    @PutMapping("/{id}")
    public ResponseEntity<ObjectResponse> update(@PathVariable("id") Integer id, @RequestBody OrderDTO orderDTO) {
        try {
            var order = Order.convert(orderDTO);
            var newOrder = orderService.update(id, order);
            var newOrderDTO = OrderDTO.convert(newOrder);
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("success", newOrderDTO, HttpStatus.OK.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectResponse("error", e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
}

