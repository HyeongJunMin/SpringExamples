package jpabook.jpashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

/**
 * 공유 참조로 인해 발생하는 부작용을 피하기 위해
 * 불변객체로 설계한 클래스
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Period {
  private LocalDateTime startDate;
  private LocalDateTime endDate;
}
