import datetime

import pymysql


def insert_user_info(email, username, password, name):
    # open database connection
    db = pymysql.connect("localhost", "root", "123456", "pinpal")
    # 使用 cursor() 方法创建一个游标对象 cursor
    cursor = db.cursor()
    sql = "INSERT INTO user(email, username, password, name) VALUES ('%s', '%s', '%s', '%s')" % (
        email, username, password, name)
    # 使用 execute()  方法执行 SQL 查询
    try:
        # 执行sql语句
        cursor.execute(sql)
        print("success to insert")
        # 提交到数据库执行
        db.commit()
    except:
        # 如果发生错误则回滚
        db.rollback()
    # 关闭数据库连接
    db.close()


def insert_pin_info(reg_num, vali_code):
    # open database connection
    db = pymysql.connect("localhost", "root", "123456", "pinpal")
    # 使用 cursor() 方法创建一个游标对象 cursor
    cursor = db.cursor()
    sql = "INSERT INTO pinpal(reg_num, vali_code) VALUES ('%d','%s')" % (reg_num, vali_code)
    # 使用 execute()  方法执行 SQL 查询
    try:
        # 执行sql语句
        cursor.execute(sql)
        print("success to insert")
        # 提交到数据库执行
        db.commit()
    except:
        # 如果发生错误则回滚
        db.rollback()
    # 关闭数据库连接
    db.close()


def insert_user_info(user_id, reg_num):
    # open database connection
    db = pymysql.connect("localhost", "root", "123456", "pinpal")
    # 使用 cursor() 方法创建一个游标对象 cursor
    cursor = db.cursor()
    sql = "INSERT INTO authorization (user_id,reg_num) VALUES ('%d', '%d')" % (user_id, reg_num)
    # 使用 execute()  方法执行 SQL 查询
    try:
        # 执行sql语句
        cursor.execute(sql)
        print("success to insert")
        # 提交到数据库执行
        db.commit()
    except:
        # 如果发生错误则回滚
        db.rollback()
    # 关闭数据库连接
    db.close()


def update_latest(reg_num, latest_time, latest_loca):
    # open database connection
    db = pymysql.connect("localhost", "root", "123456", "pinpal")
    # 使用 cursor() 方法创建一个游标对象 cursor
    cursor = db.cursor()
    sql = "UPDATE pinpal SET latest_time = '%s', latest_loca = '%s' WHERE reg_num = '%d'" % (
        latest_time, latest_loca, reg_num)
    try:
        # 执行SQL语句
        cursor.execute(sql)
        print("success to update")
        # 提交到数据库执行
        db.commit()
    except:
        # 发生错误时回滚
        db.rollback()
    # 关闭数据库连接
    db.close()


def get_auth_pins(user_id):
    # open database connection
    db = pymysql.connect("localhost", "root", "123456", "pinpal")
    # 使用 cursor() 方法创建一个游标对象 cursor
    cursor = db.cursor()
    reg_pins = []
    sql = "SELECT reg_num FROM authorization WHERE user_id= '%d'" % (user_id)
    try:
        # 执行SQL语句
        cursor.execute(sql)
        # 获取所有记录列表
        results = cursor.fetchall()
        for row in results:
            reg_pins.append(row[0])
    except:
        print("Error: unable to fetch data")
    return reg_pins
    # 关闭数据库连接
    db.close()


def get_latest(reg_pins):
    # open database connection
    db = pymysql.connect("localhost", "root", "123456", "pinpal")
    # 使用 cursor() 方法创建一个游标对象 cursor
    cursor = db.cursor()
    latest_info = []
    for pin in reg_pins:
        pin_info = {}
        sql = "SELECT latest_time, latest_loca FROM pinpal WHERE reg_num= '%d'" % (pin)
        try:
            # 执行SQL语句
            cursor.execute(sql)
            # 获取所有记录列表
            results = cursor.fetchall()
            for row in results:
                pin_info["reg_code"] = pin
                pin_info["time"] = str(row[0])
                if row[1] is not None:
                    pin_info["latitude"] = float(str(row[1]).split(',')[0])
                    pin_info["longitude"] = float(str(row[1]).split(',')[1])
                else:
                    pin_info["latitude"] = None
                    pin_info["longitude"] = None

                latest_info.append(pin_info)
        except:
            print("Error: unable to fetch data")
    # 关闭数据库连接
    db.close()
    return latest_info

# pin_id = 123456
# dt = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
# gps = "aa234-nw456"
# update_latest(pin_id, dt, gps)
# # dt = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
# # update_latest(123456, dt, "aa123-nw456")
# reg_pins = get_auth_pins(1)
# latest_info = get_latest(reg_pins)
# for pin_info in latest_info:
#     print(pin_info)
#     print(latest_info[pin_info][0])
#     print(latest_info[pin_info][1])

