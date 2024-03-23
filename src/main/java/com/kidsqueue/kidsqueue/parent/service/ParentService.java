package com.kidsqueue.kidsqueue.parent.service;

import com.kidsqueue.kidsqueue.common.exception.ApiException;
import com.kidsqueue.kidsqueue.parent.db.Parent;
import com.kidsqueue.kidsqueue.parent.db.ParentRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParentService {

	private final ParentRepository parentRepository;

	// id로 사용자 가져오는 메서드
	public Parent findParentById(Long id) {
		Optional<Parent> optionalParent = parentRepository.findById(id);

		// id와 일치하는 사용자 없으면 ApiException 예외 발생 -> ApiExceptionHandler 가 처리 후 응답 해줌
		if (optionalParent.isEmpty()) {
			throw new ApiException("해당 id와 일치하는 사용자가 없습니다", HttpStatus.NOT_FOUND);
		}

		// id와 일치하는 사용자 있으면 리턴
		return optionalParent.get();
	}

}
