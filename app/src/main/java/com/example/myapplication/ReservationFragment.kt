package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.fragment.app.Fragment

class ReservationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reservation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize GridLayout
        val gridLayout: GridLayout = view.findViewById(R.id.gridLayout)

        // Populate GridLayout with cinema seats
        populateCinemaSeats(gridLayout)
    }

    private fun populateCinemaSeats(gridLayout: GridLayout) {
        val rows = 8
        val cellsInRow = 10

        for (rowIndex in 0 until rows) {
            for (cellIndex in 0 until cellsInRow) {
                val seatNumber = rowIndex * cellsInRow + cellIndex + 1

                // Create Seat View
                val seatView = createSeatView()

                // Add Seat View to GridLayout
                gridLayout.addView(seatView)
            }
        }
    }

    private fun createSeatView(): View {
        val seatView = View(requireContext())

        val sizeInPx = 150

        // Tworzymy nowe parametry układu z marginesami
        val layoutParams = ViewGroup.MarginLayoutParams(sizeInPx, sizeInPx)

        // Ustawiamy parametry marginesów na 8 pikseli dla każdego boku
        layoutParams.setMargins(8, 8, 8, 8)

        // Ustawiamy parametry dla naszego widoku
        seatView.layoutParams = layoutParams

        // Ustawiamy białe tło dla każdego miejsca
        seatView.setBackgroundColor(Color.White.toArgb())

        return seatView
    }
}
