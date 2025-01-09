package com.example.mytestapplication2.journal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mytestapplication2.databinding.FragmentJournalEntryBinding

class JournalEntryFragment : Fragment() {

    private var _binding: FragmentJournalEntryBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
}