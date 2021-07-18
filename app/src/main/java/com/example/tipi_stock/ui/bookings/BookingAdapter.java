package com.example.tipi_stock.ui.bookings;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tipi_stock.R;
import com.example.tipi_stock.backend.data.Booking;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

    private List<Booking> bookingData;
    private LayoutInflater layoutInflater;

    public BookingAdapter(Context context) {
        // Create a layout inflater from the instantiating fragments context
        layoutInflater = LayoutInflater.from(context);
    }

    /**
     * Overridden method for creating and initialising a ViewHolder and related View
     *
     * @param parent new views added to the parent ViewGroup
     * @param viewType view type of new view
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(
                R.layout.booking_card_view, parent, false);
        ViewHolder holder = new ViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.ViewHolder cardHolder, int position) {
        System.out.println(position);
        if (bookingData != null ) {
            cardHolder.setRowData(bookingData.get(0));
        } else {
            System.out.println("hi");
        }

        // Bind the current ViewHolder with an on click listener that removes the item
        cardHolder.binButton.setOnClickListener(view -> {

            // Obtain the view context to be used to display the dialog
            Context viewContext = view.getRootView().getContext();

            // Instantiate an AlertDialog builder for displaying the message popup
            AlertDialog.Builder deleteDialog = new AlertDialog.Builder(viewContext);
            deleteDialog.setMessage("Are you sure you want to delete this booking?");
            deleteDialog.setPositiveButton("Confirm", (dialogInterface, i) -> {
                bookingData.remove(cardHolder.getAbsoluteAdapterPosition());
                // Notify listeners (RecyclerView) of the change in data
                notifyDataSetChanged();
            });
            deleteDialog.setNegativeButton("Cancel", (dialogInterface, i) -> {

            });

            deleteDialog.create().show();
        });
    }

    /**
     * Accessor for obtaining the number of elements
     * @return size of dataset
     */
    @Override
    public int getItemCount() {
        if (bookingData != null) {
            return bookingData.size();
        } else {
            return 0;
        }
    }

    void setBookings(List<Booking> currentBookings) {
        bookingData = currentBookings;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder class provides a wrapper for around a View, contains layout for individual
     * items in the list
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView bookingTextView, customerTextView, dateStartTextView, dateEndTextView;
        ImageButton binButton, modifyButton;

        /**
         * ViewHolder constructor
         * @param itemView individual row item view
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Obtain the required view from recycler_row.xml
            bookingTextView = itemView.findViewById(R.id.structure_type_text);
            customerTextView = itemView.findViewById(R.id.customer_text);
            dateStartTextView = itemView.findViewById(R.id.start_date_text);
            dateEndTextView = itemView.findViewById(R.id.end_date_text);

            binButton = itemView.findViewById(R.id.bin_button);
        }

        /**
         * Set the booking item data
         * @param booking booking object passed when a ViewHolder binds
         */
        public void setRowData(Booking booking) {

            // Set the text of all required views
            bookingTextView.setText(booking.getStructureType());
            customerTextView.setText("Customer name: " + booking.getCustomerFirstName() +
                    " " + booking.getCustomerLastName());
            dateStartTextView.setText(booking.getBookingStartDate().toString());
        }
    }



}
