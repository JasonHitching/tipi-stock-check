package com.example.tipi_stock.ui.bookings.booking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tipi_stock.R;
import com.example.tipi_stock.ui.bookings.SharedBookingViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Booking fragment displays the booking recycler and existing bookings from the Room database
 *
 * Select functionality from Android Jetpack library classes are utilised
 * to achieve some of the required functionality for this class:
 *      https://developer.android.com/reference/androidx/fragment/app/Fragment
 */
public class BookingFragment extends Fragment {

    private static BookingAdapter bookingAdapter;
    private static RecyclerView bookingRecycler;
    private SharedBookingViewModel sharedBookingViewModel;

    private View rootView;

    // Required empty constructor
    public BookingFragment() {
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
                             @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.booking_fragment, null);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        bookingRecycler = rootView.findViewById(R.id.booking_recycler);
        bookingAdapter = new BookingAdapter(getActivity());
        bookingRecycler.setHasFixedSize(true);
        bookingRecycler.setAdapter(bookingAdapter);
        bookingRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        sharedBookingViewModel = new ViewModelProvider(this).get(SharedBookingViewModel.class);

        // Add a live data observer
        sharedBookingViewModel.getAllBookings().observe(this, bookings -> bookingAdapter.setBookings(bookings));

        FloatingActionButton newBookingFab = rootView.findViewById(R.id.new_booking_fab);

        newBookingFab.setOnClickListener(thisView -> {
            NavHostFragment.findNavController(this).navigate(
                    R.id.action_booking_nav_to_bookingFormFragment);
        });

    }

}