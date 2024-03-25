package com.kidsqueue.kidsqueue.favor.controller;

import com.kidsqueue.kidsqueue.common.Api;
import com.kidsqueue.kidsqueue.favor.dto.FavorDto;
import com.kidsqueue.kidsqueue.favor.dto.FavorResponse;
import com.kidsqueue.kidsqueue.favor.facade.FavorFacade;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/favor")
@RequiredArgsConstructor
public class FavorApiController {

	private final FavorFacade favorFacade;

	// 부모 아이디로 즐겨찾기 리스트 가져오기
	// /api/favor?parentId=1
	@GetMapping
	public Api<List<FavorResponse>> findAll(Long parentId) {

		List<FavorResponse> favorResponseList = favorFacade.findAll(parentId);

		return Api.ok(favorResponseList, "즐겨찾기 목록 가져오기 성공");
	}

	// 즐겨찾기 등록
	// /api/favor?parentId=1&hospitalId=1
	@PostMapping
	public Api<FavorDto> createFavor(Long parentId, Long hospitalId) {

		FavorDto favorDto = favorFacade.createFavor(parentId, hospitalId);

		return Api.ok(favorDto, "즐겨찾기 등록 성공");

	}

	// 즐겨찾기 삭제
	// /api/favor/1
	@DeleteMapping("/{id}")
	public Api<FavorDto> deleteFavor(
		@PathVariable Long id
	) {

		FavorDto favorDto = favorFacade.deleteFavor(id);

		return Api.ok(favorDto, "즐겨찾기 삭제 성공 ( is_active=0 )");

	}

}
