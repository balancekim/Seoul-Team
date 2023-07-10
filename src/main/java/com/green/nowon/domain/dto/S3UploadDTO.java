package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.CategoryEntity;
import com.green.nowon.domain.entity.GoodsEntity;
import com.green.nowon.domain.entity.GoodsImageEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class S3UploadDTO {
	
	private String tempKey;
	private String bucketKey;
	private String orgName;
	private String newName;
	private boolean def;
	private long gno; //fk 상품pk
	
	public S3UploadDTO withGno(long no) {
        this.gno = no;
        return this;
    }
	
	public S3UploadDTO bucketKey(String bucketKey) {
		this.bucketKey=bucketKey;
		return this;
	}
	
	public GoodsImageEntity toEntity(long no) {
		return GoodsImageEntity.builder()
				.bucketKey(bucketKey).orgName(orgName).def(def).goods(GoodsEntity.builder().no(no).build())
				.build();
	}
}
