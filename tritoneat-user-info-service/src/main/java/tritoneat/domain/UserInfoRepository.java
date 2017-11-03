package tritoneat.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserInfoRepository extends PagingAndSortingRepository<UserInfo, String> {

    Page<UserInfo> findByUserName(@Param("userName") String userName, Pageable pageable);

}
