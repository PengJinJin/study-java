package root.com.design_pattern.builder;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 产品角色
 */
@NoArgsConstructor
@Data
public class Robot {
	private String head;
	private String body;
	private String hand;
	private String foot;
}
