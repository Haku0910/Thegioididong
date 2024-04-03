package com.project.tgdd_be.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tgdd_be.entities.Order;
import com.project.tgdd_be.model.dto.OrderDTO;
import com.project.tgdd_be.model.mapper.OrderMapper;
import com.project.tgdd_be.repositories.OrderRepository;
import com.project.tgdd_be.service.OrderService;

@Service
public class OrderServiceImp implements OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<OrderDTO> listAll() {
		List<OrderDTO> listOrderDTO = new ArrayList<>();
		List<Order> listOrder = orderRepository.findAll();
		for (Order order : listOrder) {
			listOrderDTO.add(OrderMapper.toOrderDTO(order));
		}
		return listOrderDTO;
	}

	@Override
	public void delete(Integer id) {
		orderRepository.deleteById(id);
	}

	@Override
	public Order getOrderById(Integer id) {
		/* return orderRepository.findById(id).get(); */
		return orderRepository.findById(id).get();
	}

	@Override
	public List<OrderDTO> listOrderBySpecificPhone(String query) {
		List<OrderDTO> listOrderDTO = new ArrayList<>();
		List<Order> listOrder = orderRepository.listOrderBySpecificPhone(query);
		for (Order order : listOrder) {
			listOrderDTO.add(OrderMapper.toOrderDTO(order));
		}
		/* return orderRepository.listOrderBySpecificPhone(query); */
		return listOrderDTO;
	}

	@Override
	public Order save(Order order) {
		return orderRepository.save(order);
	}


	@Override
	public Order updateShippingStatus(Integer id, Order order) {
		if (orderRepository.findById(id).isPresent()) {
			Order existOrder = orderRepository.findById(id).get();		
			Order updateOrder = orderRepository.save(existOrder);
			return updateOrder;
		} else {
			return null;
		}
	}

	@Override
	public OrderDTO getOrderByPhoneNumber(String phoneNumber) {
		return OrderMapper.toOrderDTO(orderRepository.findByPhoneNumber(phoneNumber));
	}

	@Override
	public OrderDTO getOrderDTOById(Integer id) {
		return OrderMapper.toOrderDTO(orderRepository.findById(id).get());
	}

	@Override
	public List<OrderDTO> listAllForcus() {
		List<OrderDTO> listOrderDTO = new ArrayList<>();
		List<Order> listOrder = orderRepository.listOrderForCus();
		for (Order order : listOrder) {
			listOrderDTO.add(OrderMapper.toOrderDTO(order));
		}
		return listOrderDTO;
		
	}


}
