package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.khachhang;
import model.quantrivien;
import until.hibernateSessionFactory;

public class quantrivienDao implements interfaceDAO<quantrivien> {
	
	public static quantrivienDao getInstance() {
		return new quantrivienDao();
	}

	@Override
	public boolean insert(quantrivien t) {
		boolean b = false;
		try {
			SessionFactory SF = hibernateSessionFactory.getsessionfactory();
			if(SF!=null) {
				Session session = SF.openSession();
				try {
					Transaction tran = session.beginTransaction();
					session.save(t);
					tran.commit();
					b = true;
				} finally {
					session.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean update(quantrivien t) {
		boolean b = false;
		try {
			SessionFactory SF = hibernateSessionFactory.getsessionfactory();
			if(SF!=null) {
				Session session = SF.openSession();
				try {
					Transaction tran = session.beginTransaction();
					session.update(t);
					tran.commit();
					b = true;
				} finally {
					session.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean delete(quantrivien t) {
		boolean b = false;
		try {
			SessionFactory SF = hibernateSessionFactory.getsessionfactory();
			if(SF!=null) {
				Session session = SF.openSession();
				try {
					Transaction tran = session.beginTransaction();
					session.delete(t);
					tran.commit();
					b = true;
				} finally {
					session.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public List<quantrivien> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public quantrivien selectById(quantrivien t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<quantrivien> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public quantrivien selectByTendangnhap(String tenDangNhap) {
		List<quantrivien> arr = new ArrayList<quantrivien>();
		try {
			SessionFactory SF = hibernateSessionFactory.getsessionfactory();
			if(SF!=null) {
				Session session = SF.openSession();
				try {
					Transaction tran = session.beginTransaction();
					String hsql = "from quantrivien q where q.tendangnhap=:tendangnhap";
					Query query = session.createQuery(hsql);
					query.setParameter("tendangnhap", tenDangNhap);
					arr = query.getResultList();
					tran.commit();
				} finally {
					session.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(arr.size() > 0) {
			return arr.get(0);
		}else {
			return null;
		}
	}

}
