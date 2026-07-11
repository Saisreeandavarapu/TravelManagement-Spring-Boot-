package com.travel.TravelManagement.repo;

import com.travel.TravelManagement.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findByregistrationId(Long id);

    List<Booking> findByaPackageId(Long id);
    @Query("""
       SELECT COUNT(b.id)
       FROM Booking b
       WHERE b.aPackage.driver.id = :driverId
       """)
    Long countBookingByDriver(Long driverId);
    @Query("""
select count(p.id)
from Packages p
where p.driver.id = :userId
""")
    Long countPackagesByDriver(Long userId);

    @Query("""
select count(b.id)
from Booking b
where b.aPackage.driver.id = :userId
and b.bookingStatus = 'PENDING'
""")
    Long countPendingPacakgesByDriver(Long userId);
    @Query("""
select count(b.id)
from Booking b
where b.aPackage.driver.id = :userId
and b.bookingStatus = 'CONFIRMED'
""")
    Long countApprovedPackagesByDriver(Long userId);

    @Query("""
select count(b.id)
from Booking b
where b.aPackage.driver.id = :userId
and b.bookingStatus = 'COMPLETED'
""")
    Long countCompletedTripsByDriver(Long userId);

    @Query("""
select AVG(r.rating)
from Review r
where r.aPackage.driver.id = :userId
""")
    Long averageRatingByDriver(Long userId);
    @Query("""
 SELECT COUNT(b.id)
   FROM Booking b 
    WHERE b.registration.id = :userId 
 """)
    Long totalBookingByCustomer(Long userId);
    @Query("""
            SELECT COUNT(b.id)
            FROM Booking b
            WHERE b.registration.id = :userId
            AND b.bookingStatus = 'COMPLETED'
            """)
    Long completedTripsByCustomer(Long userId);
    @Query("""
            SELECT COUNT(b.id)
            FROM Booking b
            WHERE b.registration.id = :userId
            AND b.bookingStatus = 'CANCELLED'
            """)
    Long cancelledTripsByCustomer(Long userId);
    @Query("""
            SELECT COALESCE(SUM(b.totalAmount),0)
            FROM Booking b
            WHERE b.bookingStatus='COMPLETED'
            AND b.bookingDate = CURRENT_DATE
            """)
    Double todayRevenue();
    //It checks values from left to right and returns the first one that is not NULL.
    @Query("""
SELECT COALESCE(SUM(b.totalAmount),0)
FROM Booking b
WHERE b.bookingStatus='COMPLETED'
AND b.bookingDate BETWEEN :startDate AND :endDate
""")
    Double weeklyRevenue(
            LocalDate startDate,
            LocalDate endDate);
    @Query("""
SELECT COALESCE(SUM(b.totalAmount),0)
FROM Booking b
WHERE b.bookingStatus='COMPLETED'
AND b.bookingDate BETWEEN :startDate AND :endDate
""")
    Double monthlyRevenue(
            LocalDate startDate,
            LocalDate endDate);
    @Query("""
       SELECT COALESCE(SUM(b.totalAmount),0)
       FROM Booking b
       WHERE b.bookingStatus = 'COMPLETED'
       AND b.bookingDate BETWEEN :startDate AND :endDate
       """)
    Double yearlyRevenue(LocalDate startDate,LocalDate endDate);
    @Query("""
            select b
            from Booking b
            where b.travelDate=:date
            and b.bookingStatus='CONFIRMED'
            """)
    List<Booking> todayBookings(LocalDate today);
}
