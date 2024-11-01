package com.promptoven.exhibitionservice.admin.exhibition.application;

import com.promptoven.exhibitionservice.admin.exhibition.dto.in.AddExhibitionRequestDto;
import com.promptoven.exhibitionservice.admin.exhibition.dto.in.DeleteExhibitionRequestDto;
import com.promptoven.exhibitionservice.admin.exhibition.dto.in.UpdateExhibitionRequestDto;
import com.promptoven.exhibitionservice.admin.exhibition.infrastructure.AdminBannerRepository;
import com.promptoven.exhibitionservice.admin.exhibition.infrastructure.AdminExhibitionProductRepository;
import com.promptoven.exhibitionservice.admin.exhibition.infrastructure.AdminExhibitionRepository;
import com.promptoven.exhibitionservice.common.domain.Banner;
import com.promptoven.exhibitionservice.common.domain.Exhibition;
import com.promptoven.exhibitionservice.common.domain.ExhibitionProduct;
import com.promptoven.exhibitionservice.global.common.response.BaseResponseStatus;
import com.promptoven.exhibitionservice.global.error.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AdminExhibitionServiceImpl implements AdminExhibitionService {

    private final AdminExhibitionRepository adminExhibitionRepository;
    private final AdminBannerRepository adminBannerRepository;
    private final AdminExhibitionProductRepository adminExhibitionProductRepository;

    @Transactional
    @Override
    public void addExhibition(AddExhibitionRequestDto exhibitionRequestDto) {

        if (Boolean.TRUE.equals(adminExhibitionRepository.existsByName(exhibitionRequestDto.getName()))) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_EXHIBITION_NAME);
        }

        Exhibition exhibition = adminExhibitionRepository.save(exhibitionRequestDto.toEntity());

        for (AddExhibitionRequestDto.BannerInfo bannerInfo : exhibitionRequestDto.getBannerInfos()) {
            Banner banner = Banner.builder()
                    .imageUrl(bannerInfo.getImageUrl())
                    .exhibitionId(exhibition.getExhibitionId())
                    .bannerOrder(bannerInfo.getBannerOrder())
                    .build();
            adminBannerRepository.save(banner);
        }

        for (Long productId : exhibitionRequestDto.getProductIds()) {
            ExhibitionProduct exhibitionProduct = ExhibitionProduct.builder()
                    .exhibitionId(exhibition.getExhibitionId())
                    .productId(productId)
                    .build();
            adminExhibitionProductRepository.save(exhibitionProduct);
        }
    }

    @Override
    public void updateExhibition(UpdateExhibitionRequestDto updateExhibitionRequestDto) {

        adminExhibitionRepository.findById(updateExhibitionRequestDto.getExhibitionId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.EXHIBITION_NOT_FOUND));

        adminExhibitionRepository.save(updateExhibitionRequestDto.toEntity());

    }

    @Override
    public void deleteExhibition(DeleteExhibitionRequestDto deleteExhibitionRequestDto) {

        Exhibition exhibition = adminExhibitionRepository.findById(deleteExhibitionRequestDto.getExhibitionId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.EXHIBITION_NOT_FOUND));

        adminExhibitionRepository.save(DeleteExhibitionRequestDto.toEntity(exhibition));

    }

}
