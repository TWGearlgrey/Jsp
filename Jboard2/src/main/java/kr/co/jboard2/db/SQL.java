package kr.co.jboard2.db;

public class SQL {
	
	//////////////////////////////////////
	// User								//
	//////////////////////////////////////
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

	public static final String SELECT_USER       			 = "SELECT * FROM `User` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	public static final String SELECT_USER_BY_NAME_AND_EMAIL = "SELECT * FROM `User` WHERE `name`=? AND `email`=?";
	public static final String SELECT_COUNT_UID  			 = "SELECT COUNT(*) FROM `User` WHERE `uid`=?";
	public static final String SELECT_COUNT_NICK 			 = "SELECT COUNT(*) FROM `User` WHERE `nick`=?";
	public static final String SELECT_COUNT_EMAIL			 = "SELECT COUNT(*) FROM `User` WHERE `email`=?";
	public static final String SELECT_COUNT_NAME_AND_EMAIL	 = "SELECT COUNT(*) FROM `User` WHERE `name`=? AND `email`=?";
	public static final String SELECT_COUNT_UID_AND_EMAIL	 = "SELECT COUNT(*) FROM `User` WHERE `uid`=? AND `email`=?";
	public static final String SELECT_COUNT_HP   			 = "SELECT COUNT(*) FROM `User` WHERE `hp`=?";
	public static final String SELECT_TERMS     	         = "SELECT * FROM `Terms`";
	public static final String UPDATE_USER_PASS              = "UPDATE `User` SET `pass`=SHA2(?, 256) WHERE `uid`=?";
	public static final String UPDATE_USER_FOR_WITHDRAW      = "UPDATE `User` SET "
																+ "`pass`=null, "
																+ "`name`=null, "
																+ "`nick`=null, "
																+ "`email`=null, "
																+ "`hp`=null, "
																+ "`role`=null, "
																+ "`zip`=null, "
																+ "`addr1`=null, "
																+ "`addr2`=null, "
																+ "`leaveDate`=NOW() "
																+ "WHERE `uid`=?";
	
	public static final String UPDATE_USER                   = "UPDATE `User` SET "
																+ "`name`=?, "
																+ "`nick`=?, "
																+ "`email`=?, "
																+ "`hp`=?, "
																+ "`zip`=?, "
																+ "`addr1`=?, "
																+ "`addr2`=? "
																+ "WHERE `uid`=?";
	
	//////////////////////////////////////
	// Article							//
	//////////////////////////////////////
	public final static String INSERT_ARTICLE = "INSERT INTO `Article` SET "
												+ "`title`=?, "
												+ "`content`=?,"
												+ "`file`=?,"
												+ "`writer`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";
	
	public final static String INSERT_COMMENT = "INSERT INTO `Article` SET "
												+ "`parent`=?, "
												+ "`content`=?,"
												+ "`writer`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";
	
	public final static String SELECT_MAX_NO = "SELECT MAX(`no`) FROM `Article`;";
	
	public final static String SELECT_ARTICLE = "SELECT * FROM `Article` AS a "
												+ "LEFT JOIN `File` AS b "
												+ "ON a.`no` = b.`ano` "
												+ "WHERE `no`=?;";
	
	public final static String SELECT_ARTICLES = "SELECT "
												+ "a.*, "
												+ "b.`nick` "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b "
												+ "ON a.writer = b.uid "
												+ "WHERE `parent`=0 "
												+ "ORDER BY `no` DESC "
												+ "LIMIT ?, 10";
	
	public final static String SELECT_ARTICLES_FOR_SEARCH = "SELECT "
															+ "a.*, "
															+ "b.`nick` "
															+ "FROM `Article` AS a "
															+ "JOIN `User` AS b "
															+ "ON a.writer = b.uid "
															+ "WHERE `parent`=0 AND `title` LIKE ? "
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
	public final static String SELECT_COUNT_TOTAL_FOR_SEARCH = "SELECT COUNT(*) FROM `Article` WHERE `parent`=0 AND `title` LIKE ?;";
	
	public final static String UPDATE_ARTICLE = "UPDATE `Article` SET `title`=?, `content`=?, `file`=? WHERE `no`=?";
	public final static String UPDATE_COMMENT = "UPDATE `Article` SET `content`=? WHERE `no`=?";
	
	public final static String UPDATE_ARTICLE_FOR_COMMENT = "UPDATE `Article` SET `comment` = `comment` + 1 WHERE `no`=?";
	public final static String DELETE_ARTICLE_FOR_COMMENT = "UPDATE `Article` SET `comment` = `comment` - 1 WHERE `no`=?";
	public final static String CURRENT_COMMENTS_COUNT = "SELECT `comment` FROM `Article` WHERE `no`=?";
	
	public final static String DELETE_ARTICLE = "DELETE FROM `Article` WHERE `no`=? OR `parent`=?;";
	public final static String DELETE_COMMENT = "DELETE FROM `Article` WHERE `no`=?;";
	
	
	
	//////////////////////////////////////
	// File								//
	//////////////////////////////////////
	public final static String INSERT_FILE = "INSERT INTO `File` SET "
												+ "`ano`=?, "
												+ "`oriName`=?, "
												+ "`newName`=?, "
												+ "`rdate`=NOW()";
	
	public final static String SELECT_FILE = "SELECT * FROM `File` WHERE `fno`=?";
	
	public final static String DELETE_FILE = "DELETE FROM `File` WHERE `ano`=?";
}
