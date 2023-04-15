package com.datamining.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.datamining.entity.Order;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderDAO extends JpaRepository<Order, Integer> {
	
	@Query(value="SELECT * FROM Orders o JOIN Order_Status os ON o.orderstatus_id = os.id WHERE os.name = 'Chờ xác nhận' "
			+ "or os.name = 'Đã xác nhận' or os.name = 'Chuẩn bị hàng' or os.name = 'Đang giao' order by os.id", nativeQuery = true)
    List<Order> findAllOrders();
	
	@Query(value="SELECT * FROM Orders o JOIN Order_Status os ON o.orderstatus_id = os.id WHERE os.name = 'Đã nhận'", nativeQuery = true)
    List<Order> findAllByCompleted();
	
	@Query(value="SELECT * FROM Orders o JOIN Order_Status os ON o.orderstatus_id = os.id WHERE os.name = 'Đã hủy'", nativeQuery = true)
    List<Order> findAllByCanceled();
}
