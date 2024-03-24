package com.kidsqueue.kidsqueue.favor.service;

import com.kidsqueue.kidsqueue.common.exception.ApiException;
import com.kidsqueue.kidsqueue.favor.entity.FavorEntity;
import com.kidsqueue.kidsqueue.favor.repository.FavorRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavorService {

	private final FavorRepository favorRepository;


	public List<FavorEntity> findAll(Long parentId) {
		return favorRepository.findAllByParentIdAndIsActive(parentId, 1);

	}

	public FavorEntity createFavor(FavorEntity favorEntity) {

		// 필수정보 세팅
		favorEntity.setIsActive(1);
		favorEntity.setCreatedBy(LocalDateTime.now());
		favorEntity.setUpdatedBy(LocalDateTime.now());

		return favorRepository.save(favorEntity);
	}

	public FavorEntity findFavorById(Long id) {

		Optional<FavorEntity> optionalFavorEntity = favorRepository.findById(id);

		// id와 일치하는 즐겨찾기 없으면 ApiException 예외 발생
		// ApiExceptionHandler 가 예외 처리해서 응답해줌
		if (optionalFavorEntity.isEmpty()) {
			throw new ApiException("해당 id와 일치하는 즐겨찾기가 없습니다.", HttpStatus.NOT_FOUND);
		}

		// 일치하는 즐겨찾기 있으면 리턴
		return optionalFavorEntity.get();

	}

}
