package com.kidsqueue.kidsqueue.favor.facade;

import com.kidsqueue.kidsqueue.favor.converter.FavorConverter;
import com.kidsqueue.kidsqueue.favor.dto.FavorDto;
import com.kidsqueue.kidsqueue.favor.dto.FavorResponse;
import com.kidsqueue.kidsqueue.favor.entity.FavorEntity;
import com.kidsqueue.kidsqueue.favor.service.FavorService;
import com.kidsqueue.kidsqueue.hospital.db.Hospital;
import com.kidsqueue.kidsqueue.hospital.service.HospitalService;
import com.kidsqueue.kidsqueue.parent.db.Parent;
import com.kidsqueue.kidsqueue.parent.service.ParentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FavorFacade {

    private final FavorService favorService;
    private final ParentService parentService;
    private final HospitalService hospitalService;

    private final FavorConverter favorConverter;

    public List<FavorResponse> findAll(Long parentId) {

        // id와 일치하는 사용자 있는지 검증 -> 없으면 예외발생
        parentService.findParentById(parentId);

        // id와 일치하는 병원 즐겨찾기 목록 가져옴
        List<FavorEntity> favorEntityList = favorService.findAll(parentId);

        // List<FavorEntity> 를 List<FavorResponse> 로 변경하여 리턴
        return favorEntityList.stream()
            .map(favorConverter::toFavorResponse)
            .toList();

    }

    public FavorDto createFavor(Long parentId, Long hospitalId) {

        // id와 일치하는 사용자 있는지 가져오기
        // 일치하는 사용자 없으면 예외발생
        Parent parent = parentService.findById(parentId);

        // di와 일치하는 병원 있는지 가져오기
        // 일치하는 병원 없으면 예외발생
        Hospital hospital = hospitalService.findById(hospitalId);

        // FavorEntity 생성
        FavorEntity favorEntity = favorConverter.toFavorEntity(parent, hospital);

        // FavorEntity 저장
        FavorEntity createdFavorEntity = favorService.createFavor(favorEntity);

        // 저장된 FavorEntity 를 FavorDto 로 변경하여 리턴
        return favorConverter.toFavorDto(createdFavorEntity);


    }

    @Transactional
    public FavorDto deleteFavor(Long id) {

        // id와 일치하는 즐겨찾기 가져오기
        // 일치하는 즐겨찾기 없으면 예외발생
        FavorEntity favorEntity = favorService.findFavorById(id);

        // 변경감지 -> update 문 날림
        favorEntity.setIsActive(0);

        // 수정된 즐겨찾기 가져오기
        // 없으면 예외발생
        FavorEntity updatedFavorEntity = favorService.findFavorById(id);

        // FavorDto 를 FavorDto 로 변경하여 리턴
        return favorConverter.toFavorDto(updatedFavorEntity);

    }
}
