package kr.co.jboard1.db;

public class SQL {
	
	// User
	public static final String INSERT_USER = "INSERT INTO `User` SET "
											+ "`uid`=?, "
											+ "`pass`=SHA2(?, 256)," 
											+ "`name`=?, "
											+ "`nick`=?, "
											+ "`email`=?, "
											+ "`hp`=?, "
											+ "`zip`=?, "
											+ "`addr1`=?, "
											+ "`addr2`=?, "
											+ "`regip`=?, "
											+ "`regDate`=NOW()";

	public static final String SELECT_USER        = "SELECT * FROM `User` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	public static final String SELECT_COUNT_UID   = "SELECT COUNT(*) FROM `User` WHERE `uid`=?";
	public static final String SELECT_COUNT_NICK  = "SELECT COUNT(*) FROM `User` WHERE `nick`=?";
	public static final String SELECT_COUNT_EMAIL = "SELECT COUNT(*) FROM `User` WHERE `email`=?";
	public static final String SELECT_COUNT_HP    = "SELECT COUNT(*) FROM `User` WHERE `hp`=?";
	public static final String SELECT_TERMS       = "SELECT * FROM `Terms`";
	
	// Article
	public final static String INSERT_ARTICLE = "INSERT INTO `Article` SET "
												+ "`title`=?, "
												+ "`content`=?,"
												+ "`writer`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";
	
	public final static String INSERT_COMMENT = "INSERT INTO `Article` SET "
												+ "`parent`=?, "
												+ "`content`=?,"
												+ "`writer`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";
	
	public final static String SELECT_ARTICLE = "SELECT * FROM `Article` WHERE `no`=?;";
	
	public final static String SELECT_ARTICLES = "SELECT "
												+ "a.*, "
												+ "b.`nick` "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b "
												+ "ON a.writer = b.uid "
												+ "WHERE `parent`=0 "
												+ "ORDER BY `no` DESC "
												+ "LIMIT ?, 10";
	
	public final static String SELECT_COMMENTS = "SELECT "
												+ "a.*, "
												+ "b.`nick` "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b "
												+ "ON a.writer = b.uid "
												+ "WHERE parent=?;";
	
	public final static String SELECT_COUNT_TOTAL = "SELECT COUNT(*) FROM `Article` WHERE `parent`=0;";
	
	public final static String UPDATE_ARTICLE_FOR_COMMENT = "UPDATE `Article` SET `comment` = `comment` + 1 WHERE `no`=?";

	public final static String DELETE_ARTICLE_FOR_COMMENT = "UPDATE `Article` SET `comment` = `comment` - 1 WHERE `no`=?";
	
	public final static String DELETE_COMMENT = "DELETE FROM `Article` WHERE `no`=?;";
}
