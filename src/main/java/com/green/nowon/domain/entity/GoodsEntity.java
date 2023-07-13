package com.green.nowon.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.green.nowon.domain.dto.GoodsListDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="goods")
@Entity
public class GoodsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
	@Column(unique = true, nullable = false)
	private String title;
	@Column(nullable = false)
	private int cost;
	@Column(nullable = false)
	private int price;
	@Column(nullable = false)
	private int state;
	@Column(nullable = false)
	private int stock;
	@Column(nullable = false)
	private String content;
	
	@JoinColumn(name = "cateNo", nullable = false)
	@ManyToOne
	private CategoryEntity category;
	
	@CreationTimestamp
	@Column(columnDefinition = "timestamp(6) null")
	private LocalDateTime createdDate;
	
	@Column(columnDefinition = "timestamp(6) null", nullable=true)
	private LocalDateTime updatedDate;
	
	@JoinColumn(name = "goods_no")
	@OneToMany(cascade = CascadeType.ALL)
	private List<GoodsImageEntity> Images;

	
	
}
